package ru.bpdu.basejava.storage;

import ru.bpdu.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size >= storage.length - 1) {
            System.out.printf("Error saving resume with uuid %s: Array is full", r.getUuid());
        } else if (Arrays.stream(storage).anyMatch(resume -> resume.getUuid().equals(r.getUuid()))) {
            System.out.printf("Error saving resume with uuid %s: Resume already exists", r.getUuid());
        } else {
            storage[size++] = r;
        }
    }

    public Resume get(String uuid) {
        Resume resume = Arrays.stream(storage).filter(r -> r.getUuid().equals(uuid)).findFirst().orElse(null);
        if (resume == null) {
            System.out.printf("Error getting resume with uuid %s: Resume not found", uuid);
        }
        return resume;
    }

    public void delete(String uuid) {
        if (Arrays.stream(storage).noneMatch(resume -> resume.getUuid().equals(uuid))) {
            System.out.printf("Error deleting resume with uuid %s: Resume not found", uuid);
        } else {
            for (int i = 0; i < size; i++) {
                storage[i] = storage[i].toString().equals(uuid) ? storage[size-- - 1] : storage[i];
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public Resume update(String uuid, Resume r) {
        if (Arrays.stream(storage).noneMatch(resume -> resume.getUuid().equals(uuid))) {
            System.out.printf("Error updating resume with uuid %s: Resume not found", uuid);
            return null;
        } else {
            Resume resume = get(uuid);
            resume.setUuid(r.getUuid());
            return resume;
        }
    }
}
