package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.UserDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface Step9ForGroupDriveReportRegistrationMapper {
    int insertGroupDriveReport(GroupDriveReportDTO.Request groupReport);

    int updateFileIdFk(Long groupReportId, Long fileIdFk);

    int updateGroupDriveReport(GroupDriveReportDTO.Request groupReport);

    int updateGroupSubmit(GroupDriveReportDTO.Request groupReport);

    List<GroupDriveReportDTO.Response> selectGroupDriveReportList(GroupDriveReportDTO.Request groupReport);

    GroupDriveReportDTO.Response selectGroupDriveReport(GroupDriveReportDTO.Request groupReport);

    long deleteGroupDriveReport(GroupDriveReportDTO.Request groupReport);

    long insertDriveReports(List<DriveReportDTO.Request> driveReports);

    long updateDriveReports(List<DriveReportDTO.Request> driveReport);

    long updateReportsSubmit(List<DriveReportDTO.Request> driveReports);

    long updateDriveReportsIdFkToNull(List<Long> driveIds);

    long updateAllGroupReportIdFk(Long groupReportIdFk);

    List<DriveReportDTO.Response> selectDriveReportList(DriveReportDTO.Request driveReport);

    List<Long> selectDriveReportIdsByGroupReportId(Long groupReportIdFk);

    List<DriveReportDTO.Response> selectDriveReportsForGroupDTO(GroupDriveReportDTO.Request groupReport);

    DriveReportDTO.Response selectDriveReport(DriveReportDTO.Request driveReport);

    List<UserDetailDTO.Submitter> selectSubmitterList();
}
