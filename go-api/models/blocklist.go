package models

import (
	"github.com/google/uuid"
	"gorm.io/gorm"
)

type BlockList struct {
	gorm.Model
	Blocker uuid.UUID `gorm:"column:userid1;type:uuid;primary_key"`
	Blocked uuid.UUID `gorm:"column:userid2;type:uuid;primary_key"`
}

func (BlockList) TableName() string {
	return "user_blocklist"
}
