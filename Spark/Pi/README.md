# Description

<img width="500" alt= "Pi description" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/4698e389-e564-4175-b5db-6b1075ea624b.png">

## Design

* Step 1: Create a pyspark program to generate random numbers and calculate the number of inside darts and outside darts.
* Step 2: Calculate Pi by submiting the PySpark job to Dataproc

# Implement
## Requirement
* Set up PySpark on GCP

Steps:
1. Enable the Google Cloud Compute Engine API
<img width="400" height="200" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/9985b3b7-b0bf-4a3f-a025-b8f48005ef66.png">

2. Create, Configure and Launch a Google Cloud Dataproc cluster

<img width="500" alt="cluster" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/c4bc82e1-d8ee-4d73-a9a1-d52a231820f0.png">

3. Create a Cloud Storage bucket
<img width="500" alt="cluster" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/4555dac6-6413-4093-9537-fcc60bb1e0ff.png">

4. In the top right corner of the console, click the Activate Cloud Shell button. 

<img width="500" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/e585db6f-7940-47b1-90db-e2b4664648d1.png">


## Prepare the program

The code is on the above py file.

* Once the Cloud Shell is activated, click on the Open Editor button in the top right corner of the Cloud Shell window. 
<img width="500" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/e585db6f-7940-47b1-90db-e2b4664648d1.png">

* Click on the new file icon beside your username to create a new file.

* Create pagerank.py file, save the file and close the editor.

## Running the program with Pyspark 

* Click on activate cloud shell like we did previously. Authenticate with Google Cloud Platform (GCP) 
```
gcloud auth login
```
 <img width="500" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/bb0a6ff1-a878-4ae1-b9ab-8c8767dfab5f.png">

* Submit the PySpark job to Dataproc
  
```
gcloud dataproc jobs submit pyspark pi.py --cluster=cluster-fdec --region=us-central1 -- --partitions 4 gs://pyspark-bucket2/output
```
> --cluster = cluster-fdec (your cluster name) <br>
> --region = us-central1 (your region name) <br>
> gs://pyspark-bucket2/output  = path to save your output  (specifying gs://pyspark-bucket2/output will automatically creates output folder, no need to create it specifically)

<img width="500" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/8033e9cd-2317-4bf1-8ac6-be5e533b8704.png">

* List the files in the output directory on Google Cloud Storage, copy them to the current directory, and append the contents of one file to another and display output
```
gsutil ls gs://pyspark-bucket2/output/
gsutil cp gs://pyspark-bucket2/output/* .
cat part-00003-f9a97f44-0c0e-4791-a1a2-20edc29ad309-c000.json >> part-00000-f9a97f44-0c0e-4791-a1a2-20edc29ad309-c000.json
cat part-00000-f9a97f44-0c0e-4791-a1a2-20edc29ad309-c000.json
```
<img width="500" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/d54641d9-ff67-4918-853a-62dd693e3cbd.png">
<img width="500" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/ca9fb2fe-4e36-4d2f-adce-445af7caa40a.png">
<img width="500" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/b69c291f-8e47-413a-b9ee-9f63531e9057.png">
* Output
<img width="500" alt="image" src="https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/096d3bf2-1ee5-455f-abf6-2bd021770888.png">


