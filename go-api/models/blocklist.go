package models

import (
	"github.com/google/uuid"
)

type BlockList struct {
	Blocker uuid.UUID `gorm:"column:userid1;type:uuid;primary_key"`
	Blocked uuid.UUID `gorm:"column:userid2;type:uuid;primary_key"`
}

func (BlockList) TableName() string {
	return "chat_data.user_blocklist"
}
