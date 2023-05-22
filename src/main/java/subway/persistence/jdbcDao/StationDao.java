package subway.persistence.jdbcDao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import subway.domain.Station;
import subway.domain.repository.StationRepository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class StationDao implements StationRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertAction;

    private RowMapper<Station> rowMapper = (rs, rowNum) ->
            new Station(
                    rs.getLong("id"),
                    rs.getString("name")
            );


    public StationDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("station")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Station insert(Station station) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(station);
        Long id = insertAction.executeAndReturnKey(params).longValue();
        return new Station(id, station.getName());
    }

    @Override
    public List<Station> findAll() {
        String sql = "select * from STATION";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Station findById(Long id) {
        String sql = "select * from STATION where id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("StationId에 해당하는 역이 존재하지 않습니다.");
        }
    }

    @Override
    public void update(Station newStation) {
        String sql = "update STATION set name = ? where id = ?";
        jdbcTemplate.update(sql, new Object[]{newStation.getName(), newStation.getId()});
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from STATION where id = ?";
        jdbcTemplate.update(sql, id);
    }
}