# Description

<img width="500" alt= "Pi description" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/5c7ef3ed-406b-4e74-9b9e-ef810f954e27.png">

## Design

* Step 1: Plase draw three tables to show the processes done by mapper, combiner, and reducer to show the Full Inverted Index of these three files:

* file 0's content "it is what it is"
* file 1's content "what is it"
* file 2's content "it is a banana"
    
* Step 2: Convert a WordCount MapReduce program into a Partial Inverted Index MapReduce program with the three input files and expected output.
* Step 3: Convert a Partial Inverted Index MapReduce program into a Full Inverted Index MapReduce program with the three input files and expected output.
* Step 4: Use a VM (local or Cloud) or use Eclipse to create a JAR file and then copy the JAR file to Hadoop for processing.


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
  $ mkdir FullInvertedIndex
  $ cd FullInvertedIndex
  $ cd input
  $ vi file0
  $ vi file1
  $ vi file2
```


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
<img width="700" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/a2da0541-eb94-4636-8a0a-aec14e8dd381.png">

## Make the HDFS directories required to execute MapReduce jobs(Copy input data to HDFS)
```
  $ cd ..
  $ cd hadoop-3.3.4/
  $ bin/hdfs namenode -format
  $ sbin/start-dfs.sh
  $ wget http://localhost:9870/
  $ bin/hdfs dfs -mkdir /user
  $ bin/hdfs dfs -mkdir /user/tvaitla1449
  $ bin/hdfs dfs -mkdir /user/tvaitla1449/index
  $ bin/hdfs dfs -mkdir /user/tvaitla1449/index/input
  $ bin/hdfs dfs -put ../FullInvertedIndex/input /user/tvaitla1449/index/input
```
> If you can not copy input into hadoop dictionary, please restart the virtual machine.

## Prepare code

* Build Full Inverted Index java file
```
  $ cd FullInvertedIndex
  $ vi InvertedIndex.java      
```

* Compile InvertedIndex.java and create a jar
```
  $ bin/hadoop com.sun.tools.javac.Main InvertedIndex.java
  $ cp ../FullInvertedIndex/*.class . 
  $ cp ../FullInvertedIndex/*.java .
  $ jar cf wc.jar InvertedIndex*class  
```

## Run

* Execute
```
  $ bin/hadoop jar wc.jar InvertedIndex /user/tvaitla1449/index/input /user/tvaitla1449/index/output
```

* Output
```
  $ bin/hdfs dfs -ls /user/tvaitla1449/index/output
  $ bin/hdfs dfs -cat /user/tvaitla1449/index/output/part-r-00000 
```

* Stop
```
  $ sbin/stop-dfs.sh
```

## Test Result

Test Case:

<img width="700" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/f8b567c4-c709-4603-954f-041cc2d7df12.png">


## Detail Design Presentation
[Full Inverted Index using MapReduce](https://docs.google.com/presentation/d/1aTvNJYheWPURPCg4rcxIk9d0VDTP9hBArF9o7G1TBLM/edit?usp=sharing)
