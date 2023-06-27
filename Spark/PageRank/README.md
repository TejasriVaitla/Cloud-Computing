# 1.PageRank Theory

<img width="200" alt="pagerank theory" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/887e34ea-6d64-49e5-bcd0-1c1c4bac2378.png">


1. Assume The initial PageRank value for each webpage is 1. <br>
PR(A) = 1  <br>
PR(B) = 1  <br>
PR(C) = 1  <br>
Page B has a link to pages C and A<br>
Page C has a link to page A<br>
Page D has links to all three pages<br>

2. Then <br>
A's PageRank is: PR(A) = (1-d) + d * (PR(B) / 2 + PR(C) / 1 + PR(D) / 3)<br>
B's PageRank is: PR(B) = (1-d) + d * (PR(D) / 3)<br>
C's PageRank is: PR(C) = (1-d) + d * (PR(B) / 2 + PR(D) / 3)<br>
D's PageRank is: PR(D) = 1-d<br>
Damping factor is 0.85 <br>

3. 1st iteration <br>

  * Page B would transfer half of its existing value, or 0.5, to page A and the other half, or 0.5, to page C.
  * Page C would transfer all of its existing value, 1, to the only page it links to, A.
  * Since D had three outbound links, it would transfer one third of its existing value, or approximately 0.33, to A.
  * PR(A)= (1-d) + d * (PR(B) / 2 + PR(C) / 1 + PR(D) / 3) = (1 - 0.85) + 0.85 * (0.5 + 1 + 0.33) = 1.71
  * PR(B)= (1-d) + d * (PR(D) / 3)= (1 - 0.85) + 0.85 * 0.33 = 0.43
  * PR(C)= (1-d) + d * (PR(B) / 2 + PR(D) / 3)= (1 - 0.85) + 0.85 * (0.5 + 0.33) = 0.86
  * PR(D)= 1-d = 0.15


# 2.PageRank + PySpark + GCP

## 2.1 Set up PySpark on GCP

Steps:
1. Enable the Google Cloud Compute Engine API
<img width="400" height="200" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/9985b3b7-b0bf-4a3f-a025-b8f48005ef66.png">

2. Create, Configure and Launch a Google Cloud Dataproc cluster

<img width="500" alt="cluster" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/c4bc82e1-d8ee-4d73-a9a1-d52a231820f0.png">

3. Create a Cloud Storage bucket
<img width="500" alt="cluster" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/4555dac6-6413-4093-9537-fcc60bb1e0ff.png">

4. In the top right corner of the console, click the Activate Cloud Shell button. 

<img width="500" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/e585db6f-7940-47b1-90db-e2b4664648d1.png">


## 3.2 Do this question using PySpark

0. Questions
<img width="200" alt="pagerank" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/b2445701-70bc-49d2-8996-3b4a1f81b243.png">

1. Manually	calculate the	first	2	iteration	of	the	PageRank

* Frist	iteration  <br>
PR(A) = 1-d + d*PR(C)             = (1- 0.85) + 0.85*1 = 1 <br>
PR(B) = 1-d + d*PR(A)/2           = (1 - 0.85) + 0.85 * 0.5 = 0.575 <br>
PR(C) = 1-d + d* (PR(A)/2+PR(B))  = (1 - 0.85) + 0.85 *( 0.5 + 1) = 1.425

* Second	iteration <br>
A	=	1  <br>
B	=	(1/2)	=	0.575   <br>
C	=	0.575	+	(1/2)	=	1.425   <br>
PR(A) = 1-d + d*PR(C)              = (1- 0.85) + 0.85 * 1.425 = 1.36125   <br> 
PR(B) = 1-d + d*PR(A)/2            = (1 - 0.85) + 0.85 * 0.5 = 0.575    <br>
PR(C) = 1-d + d* (PR(A)/2+PR(B))   = (1 - 0.85) + 0.85 *( 0.5 + 0.575) = 1.06375

2. Prepare Data input data file
   
* create input folder in bucket 
<img width="200" alt="pagerank" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/f100a171-fd13-466e-acba-b2f9e1652fb6.png">

* Upload input data file to input folder
<img width="200" alt="pagerank" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/957639ed-11dd-480d-901a-ad44225b7b3d.png">

Data
```
A B
A C
B C
C A
```

3. Prepare the program

The code is on the above py file.

* Once the Cloud Shell is activated, click on the Open Editor button in the top right corner of the Cloud Shell window. 
<img width="500" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/e585db6f-7940-47b1-90db-e2b4664648d1.png">

* Click on the new file icon beside your username to create a new file.

* Create pagerank.py file, save the file and close the editor.

4. Running the program with Pyspark 

* Click on activate cloud shell like we did previously. Authenticate with Google Cloud Platform (GCP) 
```
gcloud auth login
```
 <img width="500" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/bb0a6ff1-a878-4ae1-b9ab-8c8767dfab5f.png">

* Submit the PySpark job to Dataproc
  
```
gcloud dataproc jobs submit pyspark pagerank.py     --cluster=cluster-387a     --region=us-central1     --     gs://pyspark-bucket2/input/pagerank.txt 1
```

> 1 is the iteration count  <br>
> --cluster = cluster-387a (your cluster name) <br>
> --region = us-central1 (your region name) <br>
> gs://pyspark-bucket2/input/pagerank.txt   = path to your input file pagerank.txt

## 3.3 Result

* First iteration
<img width="500" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/20e1648e-ff57-4557-9e6e-7a8259d2ea10.png">

The result is same as we calculate by hand.

* Second iteration
<img width="500" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/4e7f37ca-6b26-4e45-bd70-4a90c754e3ed.png">

The result is same as we calculate by hand.


# 4. PageRank + Scala + GCP

## 4.1 Set up Scala on GCP


Steps:
1. Create a Cloud Storage bucket 
<img width="500" alt="cluster" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/4555dac6-6413-4093-9537-fcc60bb1e0ff.png">

2. Create a Dataproc cluster 
<img width="500" alt="cluster" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/c4bc82e1-d8ee-4d73-a9a1-d52a231820f0.png">

3. Connecting to the Master Node using Secure Shell (ssh) 

<img width="500" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/12bc8e58-cb41-4638-903b-847b1ab5d758.png">

4. install scala
```
$ curl -fL https://github.com/coursier/launchers/raw/master/cs-x86_64-pc-linux.gz | gzip -d > cs && chmod +x cs && ./cs setup
$ export SCALA_HOME=/usr/local/share/scala 
$ export PATH=$PATH:$SCALA_HOME/ 
```

## 4.2 Do this question using Scala

2. Prepare Data input data file
   
* create input folder in bucket 
<img width="200" alt="pagerank" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/f100a171-fd13-466e-acba-b2f9e1652fb6.png">

* Upload input data file to input folder
<img width="200" alt="pagerank" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/957639ed-11dd-480d-901a-ad44225b7b3d.png">

Data
```
A B
A C
B C
C A
```

3. Prepare the program and Runing the program
```
spark-shell
```
<img width="500" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/121f4efb-62d2-4407-b67f-fbe30e726633.png">


* First iteration
 
<img width="500" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/d19412de-030d-4be0-a37a-c48b923de835.png">

* Second iteration

<img width="500" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/c396b1cc-0f34-4bd3-9bf0-3b4753536891.png">


# 5 Shutting down the Cluster and bucket

 * Delete or Stop cluster
 <img width="500" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/863468df-6ce1-42cf-a5ef-171dc3601749.png">

 * Delete bucket
<img width="500" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/32bf3a4d-5544-42b4-b848-e7cbad9c685f.png">

# 6. Detail Design Presentation 
