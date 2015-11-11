# Writing Executable Use Case Fixtures in Ruby #

_(experimental; uses JRuby as the Script Engine)_

```

Flow "User navigates to home page" do

  # do something useful, like drive a browser

end

Expectation "User sees home page with articles" do

  # Assert user sees page with articles

end

# use regex here (similar to Cucumber) to get text details and pass to Ruby block
Flow "User clicks \"(.*)\" link" do | linkName |

  # do something useful

end


```

**Couple things to take note:**

  * In Ruby scripts, a Context variable has already been set in the scope of your closure as `@context`.  Use as above.
  * Ruby declarations are executed under JRuby, and users are under the same restrictions as any other JRuby embedded application.