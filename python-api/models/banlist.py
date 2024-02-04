import uuid

from sqlalchemy.dialects.postgresql import UUID

from db import db, ma


class BanListModel(db.Model):
    __table_args__ = { "schema": "chat_data" }
    __tablename__ = "server_banlist"

    server_id = db.Column(
        "serverid",
        UUID(as_uuid=True), nullable=False, primary_key=True
    )
    user_id = db.Column(
        "userid",
        UUID(as_uuid=True), nullable=False, primary_key=True
    )
    created_time = db.DateTime(timezone=True)


class BanListSchema(ma.SQLAlchemySchema):
    class Meta:
        model = BanListModel

    server_id = ma.auto_field()
    user_id = ma.auto_field()
    created_time = ma.auto_field()
