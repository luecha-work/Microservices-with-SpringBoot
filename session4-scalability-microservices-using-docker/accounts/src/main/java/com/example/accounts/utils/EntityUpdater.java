package com.example.accounts.utils;

import java.lang.reflect.Field;

public class EntityUpdater {
    /**
     * อัปเดตค่าฟิลด์ใน target จาก source โดยอิงจากฟิลด์ที่ไม่เป็น null ใน source
     *
     * @param source อ็อบเจกต์ที่มีค่าฟิลด์ใหม่
     * @param target อ็อบเจกต์ที่ต้องการอัปเดต
     * @throws RuntimeException เมื่อเกิดข้อผิดพลาดในการ Reflection
     */
    public static void updateNonNullFields(Object source, Object target) {
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(source);
                if (value != null) {
                    try {
                        Field targetField = target.getClass().getDeclaredField(field.getName());
                        targetField.setAccessible(true);
                        targetField.set(target, value);
                    } catch (NoSuchFieldException e) {
                        // Skip field if it doesn't exist in the target class
                        continue;
                    }
                }
            } catch (IllegalAccessException e) {
                // Log หรือจัดการข้อผิดพลาด
                e.printStackTrace();
            }
        }
    }
}
