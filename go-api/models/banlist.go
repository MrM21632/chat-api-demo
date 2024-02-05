package models

import (
	"time"

	"github.com/google/uuid"
)

type BanList struct {
	ServerID uuid.UUID `gorm:"column:serverid;type:uuid;primary_key"`
	UserID   uuid.UUID `gorm:"column:userid;type:uuid;primary_key"`
	Occurred time.Time `gorm:"created_time"`
}

func (BanList) TableName() string {
	return "chat_data.server_banlist"
}
