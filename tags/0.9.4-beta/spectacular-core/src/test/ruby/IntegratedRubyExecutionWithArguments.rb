Flow "User does ${word} basic" do | word |

  puts "USER DOES SOMETHING BASIC: #{word}"

end


Expectation "User ${userAction} a ${typeOfView} view" do | userAction, typeOfView |

  puts "USER DOES ACTION: #{userAction} / TYPE OF VIEW:  #{typeOfView}"

end