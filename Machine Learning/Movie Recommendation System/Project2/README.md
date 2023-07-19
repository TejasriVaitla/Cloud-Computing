# Project: Movie Recommendation with MLlib - Collaborative Filtering (implementation 3)


## Introduction
This project implements Movie Recommendation using Collaborative Filtering with MLlib's Alternating Least Squares (ALS) algorithm. Collaborative Filtering is a technique used in recommendation systems to suggest items to users based on their past interactions and the preferences of other similar users.

* Step 1: Study PySpark Collaborative Filtering with ALS
  * 1.1. Study the PySpark Collaborative Filtering with ALS documentation.

  * 1.2. Download the required datasets for the project: <br>
  movies.csv: Contains movieId, title, and genre information.<br>
  ratings.csv: Contains movie ratings given by users.<br>
  tags.csv: Contains user tags associated with movies.<br>

* Step 2: Study Colab
  * 2.1. Get familiar with Google Colab, a cloud-based Jupyter notebook environment.
  
  * 2.2. Explore the "Project Falling Detection: Python + kNN + Colab" project on Colab.

* Step 3: Experiment PySpark code with ALS
  * 3.1. Download the PySpark code (ipynb) for the Movie Recommendation project.
  
  * 3.2. Upload the downloaded ipynb file to your Colab environment.
  ![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/c5ff13a8-d42c-47d3-9948-2725075a810a)

  * 3.3. Experiment with the PySpark code by modifying the ipynb file to understand the Collaborative Filtering process.

  * 3.4. Save the modified ipynb file as a .py format to be used in further steps.
  * 3.5. Run the .py file on Google Cloud Platform's Dataproc cluster to scale up the recommendation process.
## Implementation on GCP
* Create a Dataproc cluster
* Create bucket and upload python script file and input files 
* Submit a job
 > Ensure you have your Python script and input files (if any) uploaded to a GCS bucket. <br>
 > Open Google Cloud Shell. <br>
 > Use the following command to submit the job to the Dataproc cluster:
```
gcloud dataproc jobs submit pyspark gs://pyspark_bucket3/recommendation_engine_movielens.py \
    --cluster=cluster-92ba \
    --region=us-central1
```
## Output

![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/826d3a2d-94a5-4785-be57-4d81f9849196)
![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/4ef75368-0655-40de-afb9-d243db28bbbc)
![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/ebbce331-7a42-4bab-95aa-23fd5faa49ba)



## Detail Design Presentation 

[Movie Recommendation with MLlib](https://docs.google.com/presentation/d/1fjnYwc3IrHJf0ILBr--M7n0a1BAOY6D8pCyalcDs43A/edit?usp=sharing)
