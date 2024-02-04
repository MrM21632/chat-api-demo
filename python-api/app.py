import os

from dotenv import load_dotenv
from flask import Flask

from api import users
from db import initialize_database

load_dotenv()  # Load in configuration from .env


app = Flask(__name__, instance_relative_config=True)


# Configuration: database URI, debug secret
hostname = os.getenv("DB_HOST")
username = os.getenv("DB_USER")
password = os.getenv("DB_PASSWORD")
db_name = os.getenv("DB_NAME")
db_port = os.getenv("DB_PORT")

database_uri = f"postgresql+psycopg2://{username}:{password}@{hostname}:{db_port}/{db_name}"
app.config["SQLALCHEMY_DATABASE_URI"] = database_uri
app.config["SECRET_KEY"] = os.getenv("SECRET_KEY")


# Initialize database and other connections
initialize_database(app)


 # Top-level route setup
@app.route("/hello")
def hello():
    return "Hello, World!"

# Add custom routes
app.register_blueprint(users.bp)
