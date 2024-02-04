import uuid

from sqlalchemy.dialects.postgresql import UUID

from db import db, ma


class UserModel(db.Model):
    __tablename__ = "useracct"

    id = db.Column(
        "userid",
        UUID(as_uuid=True), nullable=False, primary_key=True, default=uuid.uuid4
    )
    username = db.Column(db.String(32), nullable=False, unique=True)
    email = db.Column(db.Text(), nullable=False, unique=True)
    passhash = db.Column(
        "password",
        db.Text(), nullable=False
    )
    passsalt = db.Column(db.Text(), nullable=False)


class UserSchema(ma.SQLAlchemySchema):
    class Meta:
        model = UserModel

    id = ma.auto_field()
    username = ma.auto_field()
    email = ma.auto_field()
    passhash = ma.auto_field()
    passsalt = ma.auto_field()
