package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.UserDetailDTO;

import java.util.List;

public interface Step11ForGroupOrderReportRegistrationMapper {
    int insertGroupOrderReport(GroupDriveReportDTO.Request groupReport);

    int updateFileIdFk(Long groupReportId, Long fileIdFk);

    int updateGroupOrderReport(GroupDriveReportDTO.Request groupReport);

    int updateGroupPayment(GroupDriveReportDTO.Request groupReport);

    int updateGroupPosting(GroupDriveReportDTO.Request groupReport);

    List<GroupDriveReportDTO.Response> selectGroupOrderReportList(GroupDriveReportDTO.Request groupReport);

    GroupDriveReportDTO.Response selectGroupOrderReport(GroupDriveReportDTO.Request groupReport);

    long deleteGroupOrderReport(GroupDriveReportDTO.Request groupReport);

    long insertOrderReports(List<DriveReportDTO.Request> driveReports);

    long updateOrderReports(List<DriveReportDTO.Request> driveReport);

    long updateReportsPayment(List<DriveReportDTO.Request> driveReports);

    long updateOrderReportsIdFkToNull(List<Long> driveIds);

    long updateAllGroupReportIdFk(Long groupReportIdFk);

    List<DriveReportDTO.Response> selectOrderReportList(DriveReportDTO.Request driveReport);

    List<Long> selectOrderReportIdsByGroupReportId(Long groupReportIdFk);

    List<DriveReportDTO.Response> selectOrderReportsForGroupDTO(GroupDriveReportDTO.Request groupReport);

    DriveReportDTO.Response selectOrderReport(DriveReportDTO.Request driveReport);

    List<UserDetailDTO.Driver> selectDriverList();
}
