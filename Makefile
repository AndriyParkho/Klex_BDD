all: execJDBC

testJDBC:
	javac -d lib -classpath "lib:lib/*" -sourcepath src src/tests/TestJDBC.java src/connections/JDBCUtilities.java

# Execution:
execJDBC: testJDBC
	java -classpath "lib:lib/*" tests/TestJDBC