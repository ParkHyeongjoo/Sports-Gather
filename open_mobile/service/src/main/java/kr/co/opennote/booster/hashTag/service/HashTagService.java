package kr.co.opennote.booster.hashTag.service;

import kr.co.opennote.booster.domain.HashTag;
import kr.co.opennote.booster.dto.HashTagDto;
import kr.co.opennote.booster.hashTag.repository.HashTagRepository;
import kr.co.opennote.booster.mapper.HashTagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HashTagService {

    private final HashTagRepository hashTagRepository;
    private final HashTagMapper hashTagMapper;

    public List<HashTagDto> getListByBoardUuid(String uuid) {
        List<HashTag> list = hashTagRepository.getListByBoardUuid(uuid);
        List<HashTagDto> resultList = hashTagMapper.toDto(list);
        return resultList;
    }

    public void post(List<HashTagDto> list, String uuid) {

        for(HashTagDto hashTagDto : list){
            HashTag find = hashTagRepository.getByTagNm(hashTagDto.getTagNm());

            if(find == null) {
                // 기존 hashTag가 없으면 T_HASH_TAG 에 추가
                hashTagDto.setUuid(String.valueOf(UUID.randomUUID()));
                hashTagRepository.postHashTag(hashTagMapper.toEntity(hashTagDto));
            }else{
                // 있으면 추가 X
                hashTagDto.setUuid(find.getUuid());
            }
            hashTagDto.setBoardUuid(uuid);
            hashTagDto.setTagUuid(hashTagDto.getUuid());
            // T_HASH_TAG_BOARD에 추가
            hashTagRepository.postHashTagBoard(hashTagMapper.toEntity(hashTagDto));
        }
    }

    public void deleteByBoardUuid(String uuid) {
        // 관계테이블 제거
        hashTagRepository.deleteTagBoard(uuid);
        // 해쉬태그테이블 USE_YN = 'N'
        hashTagRepository.deleteTagByBoardUuid(uuid);
    }
}
