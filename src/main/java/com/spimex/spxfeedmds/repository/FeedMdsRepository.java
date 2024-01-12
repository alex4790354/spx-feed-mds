package com.spimex.spxfeedmds.repository;

import com.spimex.spxfeedmds.general.dto.BasketOpec;
import com.spimex.spxfeedmds.general.dto.FeedContributeRequest;
import com.spimex.spxfeedmds.general.dto.RatesOpecDto;
import com.spimex.spxfeedmds.general.dto.StaticOpecDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FeedMdsRepository {

    @Insert("""
            INSERT INTO mds.central_bank_questionnaire (sid,
                                                        consensus_date,
                                                        instr_name,
                                                        tenor,
                                                        source,
                                                        unit,
                                                        crncy,
                                                        f_val_av,
                                                        f_val_mn,
                                                        f_val_mx,
                                                        f_val_md,
                                                        f_val_a_pers)
            VALUES
                (
                    #{sidFields.sid},
                    #{sidFields.fields.consensusDate},
                    #{sidFields.fields.instrName},
                    #{sidFields.fields.tenor},
                    #{sidFields.fields.source},
                    #{sidFields.fields.unit},
                    #{sidFields.fields.crncy},
                    #{sidFields.fields.valueAv},
                    #{sidFields.fields.valueMn},
                    #{sidFields.fields.valueMx},
                    #{sidFields.fields.valueMd},
                    #{sidFields.fields.valueAPers}
                )
            ON CONFLICT ON CONSTRAINT central_bank_questionnaire_unique_key
            DO
                UPDATE SET
                    instr_name=EXCLUDED.instr_name,
                    tenor=EXCLUDED.tenor,
                    source=EXCLUDED.source,
                    unit=EXCLUDED.unit,
                    crncy=EXCLUDED.crncy,
                    f_val_av=EXCLUDED.f_val_av,
                    f_val_mn=EXCLUDED.f_val_mn,
                    f_val_mx=EXCLUDED.f_val_mx,
                    f_val_md=EXCLUDED.f_val_md,
                    f_val_a_pers=EXCLUDED.f_val_a_pers,
                    updated_ts=NOW()
            """)
    void addFeedCentralBankValues(@Param("sidFields") FeedContributeRequest sidFields);

    @Insert("""
            <script>
                INSERT INTO mds.opec_basket (sid,
                                             update_date,
                                             value)
                VALUES
                  <foreach item='item' collection='opecBasket' open='' separator=',' close=''>
                    (
                        #{item.sid},
                        #{item.data},
                        #{item.val}
                    )
                  </foreach>
                ON CONFLICT ON CONSTRAINT opec_basket_unique_key
                DO
                    UPDATE SET
                        updated_ts=NOW()
            </script>
            """)
    void addAllOpecBasket(@Param("opecBasket") List<BasketOpec> opecBasket);

    @Insert("""
            INSERT INTO mds.opec_static (sid,
                                        instr_name,
                                        instr_name_eng,
                                        frequency,
                                        source,
                                        unit,
                                        crncy,
                                        prod_perm)
            VALUES
                (
                    #{staticOpecDto.sid},
                    #{staticOpecDto.instrName},
                    #{staticOpecDto.instrNameEng},
                    #{staticOpecDto.frequency},
                    #{staticOpecDto.source},
                    #{staticOpecDto.unit},
                    #{staticOpecDto.crncy},
                    #{staticOpecDto.prodPerm}
                )
            ON CONFLICT ON CONSTRAINT opec_static_unique_key
            DO
                UPDATE SET
                    instr_name=EXCLUDED.instr_name,
                    instr_name_eng=EXCLUDED.instr_name_eng,
                    frequency=EXCLUDED.frequency,
                    source=EXCLUDED.source,
                    unit=EXCLUDED.unit,
                    crncy=EXCLUDED.crncy,
                    prod_perm=EXCLUDED.prod_perm,
                    updated_ts=NOW()
            """)
    void addOpecStatic(@Param("staticOpecDto") StaticOpecDto staticOpecDto);

    @Insert("""
            INSERT INTO mds.opec_static (sid,
                                        instr_name,
                                        instr_name_eng,
                                        frequency,
                                        source,
                                        unit,
                                        crncy)
            VALUES
                (
                    'ORBCO-OPEC',
                    'Корзина ОПЕК',
                    'OPEC Reference Basket',
                    'DAILY',
                    'OPEC',
                    'bbl',
                    'USD'
                )
            ON CONFLICT ON CONSTRAINT opec_static_unique_key
            DO
                UPDATE SET
                    instr_name=EXCLUDED.instr_name,
                    instr_name_eng=EXCLUDED.instr_name_eng,
                    frequency=EXCLUDED.frequency,
                    source=EXCLUDED.source,
                    unit=EXCLUDED.unit,
                    crncy=EXCLUDED.crncy,
                    prod_perm=EXCLUDED.prod_perm,
                    updated_ts=NOW()
            """)
    void addOpecStaticJobConstant();

    @Insert("""
            INSERT INTO mds.opec_rates (sid,
                                        update_date,
                                        effective_date,
                                        value)
            VALUES
                (
                    #{ratesOpecDto.sid},
                    #{ratesOpecDto.updateDate},
                    #{ratesOpecDto.effectiveDate},
                    #{ratesOpecDto.last}
                )
            ON CONFLICT ON CONSTRAINT opec_rates_unique_key
            DO
                UPDATE SET
                    value=EXCLUDED.value,
                    updated_ts=NOW()
            """)
    void addOpecRates(@Param("ratesOpecDto") RatesOpecDto ratesOpecDto);



    @Insert("""
                INSERT INTO mds.minec_static (sid,
                        instr_name,
                        instr_name_eng,
                        frequency,
                        source_id,
                        source_name,
                        source_name_eng,
                        source_link,
                        unit,
                        unit_eng,
                        crncy)
                VALUES( #{sidFields.sid},
                        #{sidFields.fields.instrName},
                        #{sidFields.fields.instrNameEng},
                        #{sidFields.fields.frequency},
                        #{sidFields.fields.sourceId},
                        #{sidFields.fields.sourceName},
                        #{sidFields.fields.sourceNameEng},
                        #{sidFields.fields.sourceLink},
                        #{sidFields.fields.unit},
                        #{sidFields.fields.unitEng},
                        #{sidFields.fields.crncy}
                     )
                ON CONFLICT ON CONSTRAINT PK_MINEC_STATIC
                DO
                UPDATE SET
                    instr_name=EXCLUDED.instr_name,
                    instr_name_eng=EXCLUDED.instr_name_eng,
                    frequency=EXCLUDED.frequency,
                    source_id=EXCLUDED.source_id,
                    source_name=EXCLUDED.source_name,
                    source_name_eng=EXCLUDED.source_name_eng,
                    source_link=EXCLUDED.source_link,
                    unit=EXCLUDED.unit,
                    unit_eng=EXCLUDED.unit_eng,
                    crncy=EXCLUDED.crncy,
                    updated_time=now()
            """)
    void addMinecStaticValues(@Param("sidFields") FeedContributeRequest sidFields);

    @Insert("""
                INSERT INTO mds.minec_forecast (sid,
                        update_date,
                        forecast_period,
                        f_val,
                        f_val_mn)
                VALUES( #{sidFields.sid},
                        #{sidFields.fields.updateDate},
                        TO_DATE(#{sidFields.fields.forecastPeriod}, 'YYYY-MM-DD'),
                        #{sidFields.fields.fValue},
                        #{sidFields.fields.valueMn}
                     )
                ON CONFLICT ON CONSTRAINT PK_MINEC_FORECAST
                DO
                UPDATE SET
                    f_val=EXCLUDED.f_val,
                    f_val_mn=EXCLUDED.f_val_mn,
                    updated_time=now()
            """)
    void addMinecForecastValues(@Param("sidFields") FeedContributeRequest sidFields);

    @Insert("""
                INSERT INTO mds.minec_export (sid,
                        update_date,
                        effective_date,
                        value)
                VALUES( #{sidFields.sid},
                        #{sidFields.fields.updateDate},
                        #{sidFields.fields.effectiveDate},
                        #{sidFields.fields.last}
                     )
                ON CONFLICT ON CONSTRAINT PK_MINEC_EXPORT
                DO
                UPDATE SET
                    value=EXCLUDED.value,
                    updated_time=now()
            """)
    void addMinecExportValues(@Param("sidFields") FeedContributeRequest sidFields);

}
