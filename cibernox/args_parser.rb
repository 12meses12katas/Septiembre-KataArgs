  # Raises when triying to parse a flag not defined in the schema
  class UnknownFlagException < Exception; end
  # Raises when a flag receives more params than it is suposed to have or one if it is suposed to receive 0
  class UnexpectedParamException < Exception; end
  # Raises when a flag with mandatory param it is receives without that param
  class MissingParamException < Exception;   end

  class ArgsParser
    attr_accessor :schema, :args

    def initialize(hash)
      self.schema = hash
    end

    def parse(string)
      h = get_hash(string)
      invalid = h.keys.select{ |x| ! self.schema.keys.include? x }
      raise UnknownFlagException.new("Unknown flags: #{invalid.join ','}") if invalid.any?
      self.args = {}
      h.each do |flag, param| 
        raise UnexpectedParamException.new("Too much params for flag: #{flag}") if schema[flag] == nil && param
        raise MissingParamException.new("Missing param for flag: #{flag}") if param.nil? && schema[flag] != nil
        self.args[flag] = parse_param(param)
      end
    end

    private
    def get_hash(string)
      pieces = string.split /(-\D)/
      pieces.delete_at(0) if pieces[0] == ''
      i = 0
      h = {}
      while pieces[i]
        value =  pieces[i + 1]
        h[pieces[i]] = value ? value.strip : value
        i += 2
      end
      h.each{ |key, value| h[key] = nil if value && value.empty? } # Transforms empty params in nil
      h
    end

    def parse_param(p)
      case p
      when /,/ then p.split ','
      when /\d/ then p.to_i
      else p
      end
    end

  end