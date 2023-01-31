import request from '@/utils/request'

// 查询门牌列表
export function listHouse(query) {
  return request({
    url: '/standard/house/list',
    method: 'get',
    params: query
  })
}

// 查询门牌详细
export function getHouse(houseId) {
  return request({
    url: '/standard/house/' + houseId,
    method: 'get'
  })
}

// 新增门牌
export function addHouse(data) {
  return request({
    url: '/standard/house',
    method: 'post',
    data: data
  })
}

// 修改门牌
export function updateHouse(data) {
  return request({
    url: '/standard/house',
    method: 'put',
    data: data
  })
}

// 删除门牌
export function delHouse(houseId) {
  return request({
    url: '/standard/house/' + houseId,
    method: 'delete'
  })
}
