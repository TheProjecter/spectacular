# Loads steps
module Spectacular
	
	
	class StepLoader
	
		def Flow(regexString, &blockProc)
		
			wrapper = ProcBlockWrapper.new(blockProc)
			@javaCallbackObject.indexFlow(regexString, wrapper)	
			
				
		end
		
		def Expectation(regexString, &blockProc)
			
			wrapper = ProcBlockWrapper.new(blockProc)
			@javaCallbackObject.indexExpectation(regexString, wrapper)
			
		end
		
		def loadSteps(stepFilename)
			
			# first, get file
			stepFileContents = ""
			f = File.open(stepFilename, "r");
			f.each_line do |line|
				stepFileContents += line
			end
			
			# Now, eval the contents
			eval stepFileContents
				
		end
		
		def setJavaCallback(cb)
			@javaCallbackObject = cb
		end
				
		
		
	end
	
	
	class ProcBlockWrapper
		
		def initialize(block)
			@blockProc = block
		end
		
		def executeBlock(blockArgs)
		
			
			
		end
		
		
	end
	
	
	
	
end

Spectacular::StepLoader.new