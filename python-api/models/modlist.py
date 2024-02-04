import uuid

from sqlalchemy.dialects.postgresql import UUID

from db import db, ma


class ModListModel(db.Model):
    __tablename__ = "server_moderator"

    server_id = db.Column(
        "serverid",
        UUID(as_uuid=True), nullable=False, primary_key=True
    )
    user_id = db.Column(
        "userid",
        UUID(as_uuid=True), nullable=False, primary_key=True
    )
    created_time = db.DateTime(timezone=True)


class ModListSchema(ma.SQLAlchemySchema):
    class Meta:
        model = ModListModel

    server_id = ma.auto_field()
    user_id = ma.auto_field()
    created_time = ma.auto_field()
