package models

import (
	"time"

	"github.com/google/uuid"
)

type Message struct {
	ID        uuid.UUID `gorm:"column:messageid;type:uuid;primary_key;default:gen_random_uuid()"`
	ChannelID uuid.UUID `gorm:"column:channelid;type:uuid"`
	UserID    uuid.UUID `gorm:"column:userid;type:uuid"`
	Contents  string    `gorm:"column:contents"`
	Created   time.Time `gorm:"column:created_time"`
	Updated   time.Time `gorm:"column:last_modified_time"`
}

func (Message) TableName() string {
	return "chat_data.message"
}
