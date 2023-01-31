import request from '@/utils/request'

// 查询行政区列表
export function listRegion(query) {
  return request({
    url: '/standard/region/list',
    method: 'get',
    params: query
  })
}

// 查询行政区详细
export function getRegion(code) {
  return request({
    url: '/standard/region/' + code,
    method: 'get'
  })
}

// 新增行政区
export function addRegion(data) {
  return request({
    url: '/standard/region',
    method: 'post',
    data: data
  })
}

// 修改行政区
export function updateRegion(data) {
  return request({
    url: '/standard/region',
    method: 'put',
    data: data
  })
}

// 删除行政区
export function delRegion(code) {
  return request({
    url: '/standard/region/' + code,
    method: 'delete'
  })
}

// 作废/启用行政区
export function repealRegion(regionId, code, deprecated) {
  const data = {
    regionId,
    code,
    deprecated
  }
  return request({
    url: '/standard/region/changeDeprecated',
    method: 'put',
    data: data
  })
}


//入库行政区
export function auditRegion(code) {
  return request({
    url: '/standard/region/audit/' + code,
    method: 'get'
  })
}