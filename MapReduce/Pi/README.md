# Description

<img width="500" alt= "Pi description" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/4698e389-e564-4175-b5db-6b1075ea624b.png">

## Design

* Step 1: Generate an input file to the Pi MapReduce program
    * Step 1.1: Create a regular Java program which accepts two command line arguments.
    * R: The radius
    * N: The number of (x, y) pairs to create
    The Java program then randomly generates N pairs of (x, y) and displays them on the standard output.
  Step 1.2: Run the program created in Step 1.1 and save the result in a file. The file is the input to Step 2's Pi MapReduce program.

* Step 2: Create a MapReduce program to calculate the numbers of inside darts and outside darts.
* Step 3: Use the file generated in Step 1.2 as the input to execute the MapReduce program created in Step 2
* Step 4: Calculate Pi in the driver program based on the numbers of inside darts and outside darts.

# Implement

## Requirment

* GCP Environment
<img width="600" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/6d5480b2-3b96-476d-bac6-ec2c9af4230d.png">


* Hadoop environment
<img width="600" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/09deae98-6236-4c57-bf79-96d03fc7749f.png">

* Java environment
<img width="600" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/439e38a2-99ac-4dee-97ef-1af1f54ffe4b.png">

## Prepare input data
```
  $ mkdir PiCalculation
  $ cd PiCalculation
  $ vi GenerateRandomNumbers.java
  $ javac GenerateRandomNumbers.java
  $ java -cp . GenerateRandomNumbers
```

Input data will store in PiCalculationInput

## Setup passphraseless ssh
Now check that you can ssh to the localhost without a passphrase:
```
  $ cd hadoop-3.3.4/
  $ ssh localhost
```
If you cannot ssh to localhost without a passphrase, execute the following commands:
```
  $ ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
  $ cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
  $ chmod 0600 ~/.ssh/authorized_keys
```

## Make the HDFS directories required to execute MapReduce jobs(Copy input data to HDFS)
```
  $ cd ..
  $ cd hadoop-3.3.4/
  $ bin/hdfs namenode -format
  $ sbin/start-dfs.sh
  $ wget http://localhost:9870/
  $ bin/hdfs dfs -mkdir /user
  $ bin/hdfs dfs -mkdir /user/tvaitla1449
  $ bin/hdfs dfs -mkdir /user/tvaitla1449/picalculate
  $ bin/hdfs dfs -mkdir /user/tvaitla1449/picalculate/input
  $ bin/hdfs dfs -put ../PiCalculation/PiCalculationInput /user/tvaitla1449/picalculate/input
```
> If you can not copy input into hadoop dictionary, please restart the virtual machine.

## Prepare code

* Build PiCalculation java file
```
  $ cd /hadoop-3.3.4
  $ vi PiCalculation.java      
```

* Compile PiCalculation.java and create a jar
```
  $ bin/hadoop com.sun.tools.javac.Main PiCalculation.java
  $ jar cf wc.jar PiCalculation*class  
```

## Run

* Execute
```
  $ bin/hadoop jar wc.jar PiCalculation /user/tvaitla1449/picalculate/input /user/tvaitla1449/picalculate/output
```

* Output
```
  $ bin/hdfs dfs -ls /user/tvaitla1449/picalculate/output
  $ bin/hdfs dfs -cat /user/tvaitla1449/picalculate/output/part-r-00000 
```

* Stop
```
  $ sbin/stop-dfs.sh
```

## Test Result

Test Case:

<img width="600" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/1331fdfc-c6f5-4c1b-9cb0-ea06de964d6f.png">

How many random numbers to generate: 1000000
Radius = 200

<img width="700" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/fd4a980d-6563-445e-bf19-df847c1f9c06.png">


## Detail Design Presentation
[Pi calculation using MapReduce](https://docs.google.com/presentation/d/1aTvNJYheWPURPCg4rcxIk9d0VDTP9hBArF9o7G1TBLM/edit?usp=sharing)
