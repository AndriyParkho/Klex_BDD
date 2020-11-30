all: execJDBC

testJDBC:
	javac -d lib -classpath "lib:lib/*" -sourcepath src src/tests/TestJDBC.java src/tests/JDBCUtilities.java

# Execution:
execJDBC: testJDBC
	java -classpath "lib:lib/*" src.tests.TestJDBC