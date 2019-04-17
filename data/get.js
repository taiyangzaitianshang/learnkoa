const Koa = require('koa');
const app = new Koa();

app.use( async (ctx) => {
    let url = ctx.url;

    // 从ctx的request中获取信息
    let request = ctx.request;
    let req_query = request.query; // {a:1, b:2}
    let req_querystring = request.querystring; // "a=1&b=2"

    // 从ctx上下文中直接获取
    let ctx_query = ctx.query;
    let ctx_querystring = ctx.querystring;

    ctx.body = {
        url,
        req_query,
        req_querystring,
        ctx_query,
        ctx_querystring
    }
});

app.listen(3000, ()=>{
    console.log('start');
})