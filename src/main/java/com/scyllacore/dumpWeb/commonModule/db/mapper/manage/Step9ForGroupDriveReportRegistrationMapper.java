package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SubmitterDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Step9ForGroupDriveReportRegistrationMapper {
    void insertGroupDriveReport(GroupDriveReportDTO groupReport);

    void updateGroupDriveReport(GroupDriveReportDTO groupReport);

    void updateGroupSubmit(GroupDriveReportDTO groupReport);

    List<GroupDriveReportDTO> selectGroupDriveReportList(GroupDriveReportDTO groupReport);

    GroupDriveReportDTO selectGroupDriveReport(GroupDriveReportDTO groupReport);

    void deleteGroupDriveReport(GroupDriveReportDTO groupReport);

    void insertDriveReports(List<DriveReportDTO> driveReports);

    void updateDriveReports(List<DriveReportDTO> driveReport);

    void updateReportsSubmit(List<DriveReportDTO> driveReports);

    void updateDriveReportsGroupIdFk(List<Integer> driveIds);

    void updateAllGroupIdFk(int groupIdFk);

    List<DriveReportDTO> selectDriveReportList(DriveReportDTO driveReport);

    List<Integer> selectDriveReportIdsByGroupId(int groupIdFk);

    DriveReportDTO selectDriveReport(DriveReportDTO driveReport);

    List<SubmitterDTO> selectSubmitterList();
}
