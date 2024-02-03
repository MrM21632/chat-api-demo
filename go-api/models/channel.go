package models

import (
	"github.com/google/uuid"
	"gorm.io/gorm"
)

type Channel struct {
	gorm.Model
	ID       uuid.UUID `gorm:"column:channelid;type:uuid;primary_key;default:gen_random_uuid()"`
	ServerID uuid.UUID `gorm:"column:serverid;type:uuid"`
	Name     string    `gorm:"column:channel_name;unique"`
}

func (Channel) TableName() string {
	return "channel"
}
