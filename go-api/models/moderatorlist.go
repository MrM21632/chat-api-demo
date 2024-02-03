package models

import (
	"time"

	"github.com/google/uuid"
	"gorm.io/gorm"
)

type ModeratorList struct {
	gorm.Model
	Server    uuid.UUID `gorm:"column:serverid;type:uuid;primary_key"`
	Moderator uuid.UUID `gorm:"column:userid;type:uuid;primary_key"`
	Occurred  time.Time `gorm:"created_time"`
}

func (ModeratorList) TableName() string {
	return "server_moderator"
}
