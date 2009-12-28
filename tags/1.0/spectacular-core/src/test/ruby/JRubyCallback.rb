# class that calls back to java object


module Spectacular
	
	class StepLoader
		
		attr_accessor :javaCallback
		
		
		def initialize 
			@javaCallback = ""
		end
		
		def setJavaCallback(cb)
			@javaCallback = cb
		end
		
		
		
		def loadSteps(filename) 
			puts "FILENAME: #{filename}"
			
			fileContents = ""
			f = File.open filename
			f.each_line do |line|
				fileContents += line
			end
			
			eval fileContents
				
			
		end
		
		
		def Flow(flowString, &block)
			
			# do callback to java object
			procWrapper = ProcWrapper.new(block)
			@javaCallback.indexPackage(flowString, procWrapper)
			
		end
		
		
	end
	
	class ProcWrapper
		
		def initialize(block)
			@procBlock = block
		end
		
		def executeBlock(args)
			@procBlock.call(args)
		end
		
	end
	
	
	
	
end
Spectacular::StepLoader.new