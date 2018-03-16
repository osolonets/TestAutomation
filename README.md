**Instructions for QA Team:**

1. Download this project to your machine
2. Install JVM
3. Install Maven
4. Open cmd/terminal and go to dir with this project
5. Run the tests from cmd/terminal (follow instructions below)
   
   _Suite TestNG Xml Files:_
   
   * ElitePurchaseTest.xml
   * REXPurchaseTest.xml
   * REXExistingUsersTest.xml
   
   _Maven profiles:_
   
   * REX -> includes both REX TestNG Xml Files
   * Elite -> includes the only one Elite TestNG Xml File
   
   _Environments:_
   
   * Production
   * Staging (set by default)
   
   _TestTypes:_
   
   * Regression -> launches 5 high revenue states for REX and 6 professions+states for Elite
   * Sanity -> (set by default) selects a random state for REX and random profession+state for Elite
   
   _Profession:_ empty by default
    
   _State:_ empty by default

	mvn clean test -P{Maven profile} OR mvn clean test -DsuiteXmlFile={TestNG Xml File}
	
	Optionally you can add:
	
	_For all sites:_
	
	-Denvironment={Environment} -DtestType={TestType}
	
	_For Elite only:_
	
	-Dprofession={Profession} -Dstate={state}
	
	Replace {} using corresponding values chosen from the above list. You can combine them any way you need.