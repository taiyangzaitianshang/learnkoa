const Koa = require('koa');

const app = new Koa();

app.use( async(ctx) => {
    if(ctx.url === '/' && ctx.cmd === 'GET') {
        let html = `
        <h1>koa2 request post demo</h1>
        <form method="POST" action="/">
          <p>userName</p>
          <input name="userName" /><br/>
          <p>nickName</p>
          <input name="nickName" /><br/>
          <p>email</p>
          <input name="email" /><br/>
          <button type="submit">submit</button>
        </form>
        `;

        ctx.body = html;
    } else if (ctx.url === '/' && ctx.cmd === 'POST') {
        let postData = await parsePostData(ctx);
        ctx.body = postData;
    } else {
        ctx.body = '<h1>404！！！ o(╯□╰)o</h1>'
    }
})

function parsePostData(context) {
    return new Promise((res, rej)=>{
        try {
            let postData = "";
            ctx.req.addListener('data', (data) => {
                postData += data;
            });
            ctx.req.addListener('end', ()=>{
                let parseData = parseQueryStr(postData);
                res(parseData);
            })
        } catch (err) {
            reject(err);
        }
    })
}

function parseQueryStr(queryStr) {
    let queryData = {};
    let queryStrList = queryStr.split('&');
    console.log(queryStrList);
    for (let [index, queryList] of queryStrList.entries()) {
        let itemList = queryStr.split('=');
        queryData[itemList[0]] = decodeURIComponent(itemList[1]);
    }
    return queryData;
}

app.listen(3000);