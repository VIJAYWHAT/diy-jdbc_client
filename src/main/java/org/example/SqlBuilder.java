package org.example;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlBuilder {

    private final String sql;

    private final List<ParamMapper<?>> paramMappers;

    public SqlBuilder(final String theSql) {
        this.sql = theSql;
        this.paramMappers = new ArrayList<>();
    }

    private int getIndex() {
        return this.paramMappers.size() + 1;
    }

    public interface ParamMapper<T> {
        void mapParam(PreparedStatement preparedStatement) throws SQLException;
    }

    private void prepare(final PreparedStatement preparedStatement)
            throws SQLException {
        for (ParamMapper<?> paramMapper: paramMappers) {
            paramMapper.mapParam(preparedStatement);
        }
    }
}
