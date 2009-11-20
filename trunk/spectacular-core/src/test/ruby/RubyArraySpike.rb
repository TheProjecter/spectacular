# How do arrays in ruby work?
require 'test/unit'

class RubyArraySpike < Test::Unit::TestCase
  
  def test_pass_in_array
    
    myArray = Array.new
    myArray = ["omg", "hi", "you and your thing"];
    pass_me_in myArray
    pass_me_in("this", "is", "not", "your", "avg", "call")
    
  end
  
  private
  def pass_me_in(*someArr)
    
    if someArr.empty?
      puts "Array is empty"
    end
    size = someArr.size
    puts "Array size: #{size}"
      
    
    
  end
    
    
    
  
  
end