package DataAccessor;

import Model.Data;

import java.sql.SQLException;

public interface iDataController {

    public abstract void them(Data data) throws SQLException;

    public abstract void xoa(Data data);
}
