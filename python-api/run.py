import sys

from app import app
from db import db
from models.user import UserModel, UserSchema


if __name__ == "__main__":
    app.run(port=4200)
