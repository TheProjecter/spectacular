
class StepLoader
  
   def loadSteps(file)
     
     load file
  
   end
  
   def Flow(str)
  
     yield str
  
   end
  
   
end

def Flow(str)

  yield "GLOBAL FLOW #{str}"

end

