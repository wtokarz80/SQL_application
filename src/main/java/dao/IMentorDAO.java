package dao;

import java.util.List;

public interface IMentorDAO<T> {
    List<T> getMentorFirstLastNames();
    List<T> getMentorNicknamesByCity(String city);
}
