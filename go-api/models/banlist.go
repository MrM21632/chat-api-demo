package models

import (
	"time"

	"github.com/google/uuid"
	"gorm.io/gorm"
)

type BanList struct {
	gorm.Model
	ServerID uuid.UUID `gorm:"column:serverid;type:uuid;primary_key"`
	UserID   uuid.UUID `gorm:"column:userid;type:uuid;primary_key"`
	Occurred time.Time `gorm:"created_time"`
}

func (BanList) TableName() string {
	return "server_banlist"
}
