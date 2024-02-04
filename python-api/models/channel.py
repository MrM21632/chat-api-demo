import uuid

from sqlalchemy.dialects.postgresql import UUID

from db import db, ma


class ChannelModel(db.Model):
    __tablename__ = "channel"

    id = db.Column(
        "channelid",
        UUID(as_uuid=True), nullable=False, primary_key=True, default=uuid.uuid4
    )
    server_id = db.Column(
        "serverid",
        UUID(as_uuid=True), nullable=False
    )
    name = db.Column(
        "channel_name",
        db.Text(), nullable=False, unique=True
    )


class ChannelSchema(ma.SQLAlchemySchema):
    class Meta:
        model = ChannelModel

    id = ma.auto_field()
    server_id = ma.auto_field()
    name = ma.auto_field()
