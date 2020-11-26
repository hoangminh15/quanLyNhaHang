package DataAccessor;

import Model.Data;

import java.sql.SQLException;

public interface iDataController {

    void them(Data data) throws SQLException;

    void xoa(Data data);
}
