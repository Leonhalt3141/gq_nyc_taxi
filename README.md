# gq_nyc_taxi
Analyzing NYC Taxi data with Scala Spark library. Using data from Kaggle's competition, "[New York City Taxi Fare Prediction](https://www.kaggle.com/c/new-york-city-taxi-fare-prediction/)".

## Two types of prediction analysis

### 1. Simple prediction with K-means and GBT Regressor
1. Create clusters from pickup and drop-off location data
2. Use Holiday Calendar from Kaggle and create input features with passenger, date, location and distance
3. Train a model with GBT Regressor to predict taxi fare

### 2. Prediction model with Graph theory