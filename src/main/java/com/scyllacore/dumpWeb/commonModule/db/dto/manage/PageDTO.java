package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import com.scyllacore.dumpWeb.commonModule.vo.pagination.PageVO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageDTO {
    private PageVO pageInfo;
    private List<MileageDTO> pageList;
}
