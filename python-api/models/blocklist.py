import uuid

from sqlalchemy.dialects.postgresql import UUID

from db import db, ma


class BlocklistModel(db.Model):
    __table_args__ = { "schema": "chat_data" }
    __tablename__ = "user_blocklist"

    blocker_id = db.Column(
        "userid1",
        UUID(as_uuid=True), nullable=False, primary_key=True
    )
    blocked_id = db.Column(
        "userid2",
        UUID(as_uuid=True), nullable=False, primary_key=True
    )
    created_time = db.DateTime(timezone=True)


class BlocklistSchema(ma.SQLAlchemySchema):
    class Meta:
        model = BlocklistModel

    blocker_id = ma.auto_field()
    blocked_id = ma.auto_field()
    created_time = ma.auto_field()
