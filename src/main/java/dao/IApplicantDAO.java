package dao;

import java.util.List;

public interface IApplicantDAO<T> extends Searchable<T>{
    List<T> getApplicantFullNamePhoneNumberByEmailEnding(String value);
    List<T> getApplicantFullNamePhoneNumberByName(String value);
    List<T> getApplicantDataByCode(int code);
    List<T> getApplicantDataById(int id);
    List<T> getApplicantDataByFullName(String[] fullName);
    List<T> getAllUsers();
    void updatePhoneByFullName(T t, String[] params);
    void deleteByDomain(String domain);
    void addApplicant(T t);
    void updateApplicant(int id, String columnName, String newValue);
}
