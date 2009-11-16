require 'test/unit'
require 'StepLoader'


class StepLoaderTest < Test::Unit::TestCase
  
  def test_use_of_closures
    
    spectacular = StepLoader.new
    spectacular.Flow "hey there" do | str |
      
      puts "#{str}"
      
      
    end
    
  end
    
    
    
  def test_load_file_execute_flow
      
      spec = StepLoader.new
      spec.loadSteps "./SampleSteps.rb"
      
      
  end
  
end
