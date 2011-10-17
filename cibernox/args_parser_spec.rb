require './args_parser.rb'

describe ArgsParser do
  let(:schema) do
    {
     '-l' => nil,
     '-p' => Integer,
     '-d' => String, 
     '-g' => String
    }
  end
  let(:args_parser){ ArgsParser.new(schema) }
  it "should have a schema" do
    args_parser.schema.should == schema
  end
  
  it { args_parser.args.should be_nil }

  it "should raise an error if it receive an invalid flag" do
    expect { args_parser.parse '-K' }.to raise_error(UnknownFlagException)
    expect { args_parser.parse '-K -l' }.to raise_error(UnknownFlagException)    
    expect { args_parser.parse '-l' }.not_to raise_error(UnknownFlagException)    
  end


  it "should raise an error if it receives params after a flag that does not accept them" do
    expect { args_parser.parse '-l this_flag_does_not_accept_params' }.to raise_error(UnexpectedParamException)
  end

  it  "should raise an error if it does not receive params after al flag when it is mandatory" do
    expect { args_parser.parse '-p' }.to raise_error(MissingParamException)
  end  

  it "should populate args correctly" do
    args_parser.parse '-l -p 8080 -d /usr/logs -g this,is,a,list,-3,-4,6'
    args_parser.args.should == {
      '-d' => '/usr/logs',
      '-g' => ['this','is','a','list','-3','-4','6'],
      '-l' => nil,
      '-p' => 8080
    }
    args_parser.parse '-d /usr/logs -l'
    args_parser.args.should == {
      '-d' => '/usr/logs',
      '-l' => nil
    }
  end



end