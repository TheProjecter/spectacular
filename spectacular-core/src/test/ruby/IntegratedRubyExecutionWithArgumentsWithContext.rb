Flow "User does ${word} basic" do | word |

  puts "USER DOES SOMETHING BASIC: #{word}"
  @context.put("OMG", "what")
  
end


Expectation "User ${userAction} a ${typeOfView} view" do | userAction, typeOfView |

  puts "USER DOES ACTION: #{userAction} / TYPE OF VIEW:  #{typeOfView}"
  somevar = @context.get "OMG"
  puts "OMG:  #{somevar}"

  
end