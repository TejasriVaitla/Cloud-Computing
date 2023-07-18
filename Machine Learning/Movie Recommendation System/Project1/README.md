## Project: Movie Recommendation with MLlib - Collaborative Filtering (implementation 2)
* Step 1: Convert MoveLens' data (UserID, MovieID, rating, Timestamp) into the format of (UserID, MovieID, rating)
* Step 2: Implement this version of MLlib - Collaborative Filtering Example

# Introduction
This project aims to implement collaborative filtering for movie recommendation using Apache Spark's MLlib library. Collaborative filtering is a popular technique used in recommendation systems to predict and suggest items (movies in this case) to users based on their historical interactions with the items and other users' preferences.

## Step 1: Convert MoveLens' data (UserID, MovieID, rating, Timestamp) into the format of (UserID, MovieID, rating)

* Create a gcp bucket and copy movielens data file from your local machine to input folder in your bucket
```
gsutil cp gs://pyspark-bucket3/input/movielens.txt .
```
![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/7ca52212-d7da-4824-821e-e01182219311)

* Run shell script command to convert into desirable format and verify the output file
```
cat movielens.txt | while read userid movieid rating timestamp; do echo "${userid},${movieid},${rating}"; done > result.txt

cat result.txt
```
![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/4811dfc1-f0e6-4018-8d07-0ad04495c262)

* upload output file to your gcp bucket
  ```
  gsutil cp result.txt gs://pyspark-bucket3/output/
  ```
![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/bc68f50f-b23e-4f0a-bd5a-d785c184ac52)

## Step 2: Implement this version of MLlib - Collaborative Filtering Example

* Create a Dataproc cluster
* Upload your python file to bucket
* Submit a pyspark job
```
gcloud dataproc jobs submit pyspark gs://pyspark-bucket3/recommendation_example.py
```
![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/1edeaee6-0666-4f08-93c1-3a4f9838d81f)

* Output
  
![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/e39cb682-1438-4b49-98f1-2292826d982f)

# Detail Design Presentation 

[Movie Recommendation with MLlib]
