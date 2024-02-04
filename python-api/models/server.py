import uuid

from sqlalchemy.dialects.postgresql import UUID

from db import db, ma


class ServerModel(db.Model):
    __table_args__ = { "schema": "chat_data" }
    __tablename__ = "server"

    id = db.Column(
        "serverid",
        UUID(as_uuid=True), nullable=False, primary_key=True, default=uuid.uuid4
    )
    name = db.Column(
        "server_name",
        db.Text(), nullable=False, unique=True
    )


class ServerSchema(ma.SQLAlchemySchema):
    class Meta:
        model = ServerModel

    id = ma.auto_field()
    name = ma.auto_field()
