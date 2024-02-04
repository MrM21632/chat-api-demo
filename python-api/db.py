from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_marshmallow import Marshmallow

db = SQLAlchemy()
ma = Marshmallow()


def initialize_database(app: Flask):
    db.init_app(app)
    ma.init_app(app)
