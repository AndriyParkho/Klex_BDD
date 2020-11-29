all: execJDBC

testJDBC:
	javac -d lib -classpath "lib/*" -sourcepath src src/TestJDBC.java

# Execution:
execJDBC: testJDBC
	java -classpath "lib:lib/*" TestJDBC