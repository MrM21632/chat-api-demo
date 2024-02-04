import uuid

from sqlalchemy.dialects.postgresql import UUID

from db import db, ma


class MessageModel(db.Model):
    __tablename__ = "message"

    id = db.Column(
        "messageid",
        UUID(as_uuid=True), nullable=False, primary_key=True, default=uuid.uuid4
    )
    channel_id = db.Column(
        "channelid",
        UUID(as_uuid=True), nullable=False
    )
    user_id = db.Column(
        "userid",
        UUID(as_uuid=True), nullable=False
    )
    contents = db.Column(db.Text(), nullable=False)
    created_time = db.DateTime(timezone=True)
    last_modified_time = db.DateTime(timezone=True)


class MessageSchema(ma.SQLAlchemySchema):
    class Meta:
        model = MessageModel

    id = ma.auto_field()
    channel_id = ma.auto_field()
    user_id = ma.auto_field()
    contents = ma.auto_field()
    created_time = ma.auto_field()
    last_modified_time = ma.auto_field()
